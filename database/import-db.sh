#!/bin/bash

DB_USER="postgres"
DB_PASS="123456"
CONTAINER="postgres-freelance"
export PGPASSWORD=$DB_PASS

run_sql() {
  local db=$1
  local sql=$2

  docker exec -e PGPASSWORD=$PASSWORD $CONTAINER \
    psql -U postgres -d $db -c "$sql"
}

echo "🔥 Resetting ALL tables in correct order..."

echo "🟣 Clearing notifications DB..."
run_sql freelance_nexus_notifications "DELETE FROM notifications;"
run_sql freelance_nexus_notifications "ALTER SEQUENCE notifications_id_seq RESTART WITH 1;"

echo "🟡 Clearing payments DB..."
run_sql freelance_nexus_payments "DELETE FROM transaction_history;"
run_sql freelance_nexus_payments "DELETE FROM payments;"
run_sql freelance_nexus_payments "ALTER SEQUENCE payments_id_seq RESTART WITH 1;"
run_sql freelance_nexus_payments "ALTER SEQUENCE transaction_history_id_seq RESTART WITH 1;"

echo "🔵 Clearing projects DB..."
run_sql freelance_nexus_projects "DELETE FROM project_milestones;"
run_sql freelance_nexus_projects "DELETE FROM proposals;"
run_sql freelance_nexus_projects "DELETE FROM projects;"

run_sql freelance_nexus_projects "ALTER SEQUENCE project_milestones_id_seq RESTART WITH 1;"
run_sql freelance_nexus_projects "ALTER SEQUENCE proposals_id_seq RESTART WITH 1;"
run_sql freelance_nexus_projects "ALTER SEQUENCE projects_id_seq RESTART WITH 1;"

echo "🟢 Clearing freelancers DB..."
run_sql freelance_nexus_freelancers "DELETE FROM ratings;"
run_sql freelance_nexus_freelancers "DELETE FROM portfolios;"
run_sql freelance_nexus_freelancers "DELETE FROM skills;"
run_sql freelance_nexus_freelancers "DELETE FROM freelancers;"

run_sql freelance_nexus_freelancers "ALTER SEQUENCE ratings_id_seq RESTART WITH 1;"
run_sql freelance_nexus_freelancers "ALTER SEQUENCE portfolios_id_seq RESTART WITH 1;"
run_sql freelance_nexus_freelancers "ALTER SEQUENCE skills_id_seq RESTART WITH 1;"
run_sql freelance_nexus_freelancers "ALTER SEQUENCE freelancers_id_seq RESTART WITH 1;"

echo "🔴 Clearing users DB..."
run_sql freelance_nexus_users "DELETE FROM users;"
run_sql freelance_nexus_users "ALTER SEQUENCE users_id_seq RESTART WITH 1;"

echo "💚 All tables cleaned and sequences reset."

echo "🚀 Inserting USERS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_users < users.sql

echo "🚀 Inserting FREELANCERS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_freelancers < freelancers.sql

echo "🚀 Inserting PROJECTS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_projects < projects.sql

echo "🚀 Inserting PROPOSALS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_projects < proposals.sql

echo "🚀 Inserting SKILLS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_freelancers < skills.sql

echo "🚀 Inserting PORTFOLIOS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_freelancers < portfolios.sql

echo "🚀 Inserting RATINGS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_freelancers < ratings.sql

echo "🚀 Inserting PROJECT_MILESTONES..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_projects < project_milestones.sql

echo "🚀 Inserting PAYMENTS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_payments < payments.sql

echo "🚀 Inserting TRANSACTION HISTORY..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_payments < transaction_history.sql

echo "🚀 Inserting NOTIFICATIONS..."
docker exec -i $CONTAINER psql -U $DB_USER -d freelance_nexus_notifications < notifications.sql

echo "✨ Insert complete in perfect dependency order."
