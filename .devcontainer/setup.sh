#!/bin/bash

echo "🚀 Setting up development environment..."

# Install Node.js 20.x if not already installed or version is too old
if ! command -v node &> /dev/null; then
    echo "📦 Installing Node.js 20.x..."
    curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -
    sudo apt-get install -y nodejs
    echo "✅ Node.js installed successfully"
    node --version
    npm --version
else
    NODE_VERSION=$(node --version | cut -d'v' -f2 | cut -d'.' -f1)
    if [ "$NODE_VERSION" -lt 20 ]; then
        echo "⚠️  Node.js version is $NODE_VERSION, upgrading to 20.x..."
        curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -
        sudo apt-get install -y nodejs
        echo "✅ Node.js upgraded successfully"
    else
        echo "✅ Node.js $(node --version) is already installed"
    fi
fi

# Install Maven if not already installed
if ! command -v mvn &> /dev/null; then
    echo "📦 Installing Maven..."
    sudo apt-get update -qq
    sudo apt-get install -y maven
    echo "✅ Maven installed successfully"
    mvn --version
else
    echo "✅ Maven is already installed"
    mvn --version
fi

# Wait for Docker to be ready
echo "⏳ Waiting for Docker daemon..."
timeout=30
elapsed=0
until docker info &> /dev/null || [ $elapsed -eq $timeout ]; do
    sleep 1
    elapsed=$((elapsed + 1))
done

if ! docker info &> /dev/null; then
    echo "❌ Docker is not available"
    exit 1
fi

echo "✅ Docker is ready"

# Create volumes
docker volume create postgres-data 2>/dev/null || true
docker volume create rabbitmq-data 2>/dev/null || true

# Check if containers already exist
if docker ps -a | grep -q postgres-freelance; then
    echo "📦 PostgreSQL container exists, starting it..."
    docker start postgres-freelance
else
    echo "📦 Creating PostgreSQL container..."
    docker run -d \
      --name postgres-freelance \
      -e POSTGRES_USER=postgres \
      -e POSTGRES_PASSWORD=123456 \
      -p 5432:5432 \
      -v postgres-data:/var/lib/postgresql/data \
      --restart unless-stopped \
      postgres:15-alpine
    
    # Wait for PostgreSQL to be ready
    echo "⏳ Waiting for PostgreSQL to be ready..."
    sleep 10
    
    # Create databases
    echo "🗄️ Creating databases..."
    docker exec -e PGPASSWORD=123456 postgres-freelance \
      psql -U postgres -c "CREATE DATABASE freelance_nexus_users;"
    
    docker exec -e PGPASSWORD=123456 postgres-freelance \
      psql -U postgres -c "CREATE DATABASE freelance_nexus_projects;"
    
    docker exec -e PGPASSWORD=123456 postgres-freelance \
      psql -U postgres -c "CREATE DATABASE freelance_nexus_payments;"
    
    docker exec -e PGPASSWORD=123456 postgres-freelance \
      psql -U postgres -c "CREATE DATABASE freelance_nexus_notifications;"
    
    docker exec -e PGPASSWORD=123456 postgres-freelance \
      psql -U postgres -c "CREATE DATABASE freelance_nexus_freelancers;"
    
    echo "✅ All databases created"
fi

# Check if RabbitMQ container exists
if docker ps -a | grep -q rabbitmq; then
    echo "🐰 RabbitMQ container exists, starting it..."
    docker start rabbitmq
else
    echo "🐰 Creating RabbitMQ container..."
    docker run -d \
      --name rabbitmq \
      -p 5672:5672 \
      -p 15672:15672 \
      -e RABBITMQ_DEFAULT_USER=guest \
      -e RABBITMQ_DEFAULT_PASS=guest \
      -v rabbitmq-data:/var/lib/rabbitmq \
      --restart unless-stopped \
      rabbitmq:3-management-alpine
    
    echo "✅ RabbitMQ started"
fi

echo ""
echo "✅ Setup complete!"
echo "☕ Java: $(java -version 2>&1 | head -n 1)"
echo "📦 Maven: $(mvn --version | head -n 1)"
echo "🟢 Node.js: $(node --version)"
echo "📦 npm: $(npm --version)"
echo "📊 PostgreSQL: localhost:5432 (user: postgres, password: 123456)"
echo "🐰 RabbitMQ: localhost:5672"
echo "🌐 RabbitMQ Management: http://localhost:15672 (guest/guest)"
echo ""
docker ps