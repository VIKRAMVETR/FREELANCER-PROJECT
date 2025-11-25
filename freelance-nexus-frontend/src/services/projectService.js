import api from './api';

const projectService = {
 

getAllProjects: async (filters = {}) => {

  const params = new URLSearchParams();
 
  if (filters.keyword) params.append("keyword", filters.keyword);

  if (filters.category) params.append("category", filters.category);

  if (filters.status) params.append("status", filters.status);
 
  const response = await api.get(`/api/projects?${params.toString()}`);

  return response.data;

 },
 updateStatus: async (projectId, status) => {
  const response = await api.put(`/api/projects/${projectId}/status?status=${status}`);
  return response.data;
 },

  getProjectById: async (projectId) => {
    const response = await api.get(`/api/projects/${projectId}`);
    return response.data;
  },

  createProject: async (projectData) => {
    const response = await api.post('/api/projects', projectData);
    return response.data;
  },

  updateProject: async (projectId, projectData) => {
    const response = await api.put(`/api/projects/${projectId}`, projectData);
    return response.data;
  },

  deleteProject: async (projectId) => {
    const response = await api.delete(`/api/projects/${projectId}`);
    return response.data;
  },

  getClientProjects: async (clientId) => {
    const response = await api.get(`/api/projects/client/${clientId}`);
    return response.data;
  },

  getProjectsByStatus: async (status) => {
    const response = await api.get(`/api/projects/status/${status}`);
    return response.data;
  },

 searchProjects: async (query) => {
  const response = await api.get(`/api/projects?keyword=${query}`);
  return response.data;
}
};

export default projectService;