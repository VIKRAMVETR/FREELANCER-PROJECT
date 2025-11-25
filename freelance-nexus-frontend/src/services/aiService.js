import api from './api';

const aiService = {
  getProjectRecommendations: async (freelancerId) => {
    const response = await api.get(`/api/ai/recommendations/freelancer/${freelancerId}`);
    return response.data;
  },

  rankProposals: async (projectId) => {
    const response = await api.get(`/api/ai/proposals/rank/${projectId}`);
    return response.data;
  },

  generateProjectSummary: async (projectId) => {
    const response = await api.get(`/api/ai/summarize-project/${projectId}`);
    return response.data;
  },

  analyzeFreelancerProfile: async (freelancerId) => {
    const response = await api.get(`/api/ai/analyze-profile/${freelancerId}`);
    return response.data;
  },

  matchFreelancersToProject: async (projectId) => {
    const response = await api.get(`/api/ai/match-freelancers/${projectId}`);
    return response.data;
  },

  predictProjectSuccess: async (projectId) => {
    const response = await api.get(`/api/ai/predict-success/${projectId}`);
    return response.data;
  }
};

export default aiService;