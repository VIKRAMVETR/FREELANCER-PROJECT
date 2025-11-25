import api from "./api";
 
const freelancerService = {
 
  // Get full freelancer profile (profile + skills + ratings + portfolio)
  getFreelancerProfile: async (freelancerId) => {
    const res = await api.get(`/api/freelancers/${freelancerId}/profile`);
    return res.data;
  },
 
  // Get freelancer record by userId (needed for dashboard)
  getFreelancerByUserId: async (userId) => {
    const res = await api.get(`/api/freelancers/user/${userId}`);
    return res.data;
  },
 
  // Update freelancer data (title, bio, rate, skills)
  updateFreelancerProfile: async (freelancerId, profileData) => {
    const res = await api.put(`/api/freelancers/${freelancerId}`, profileData);
    return res.data;
  },
 
  // Portfolio
  getFreelancerPortfolio: async (freelancerId) => {
    const res = await api.get(`/api/portfolios/${freelancerId}/portfolio`);
    return res.data;
  },
 
  addPortfolioItem: async (freelancerId, item) => {
    const res = await api.post(`/api/portfolios/${freelancerId}/portfolio`, item);
    return res.data;
  },
 
  deletePortfolioItem: async (portfolioItemId) => {
    const res = await api.delete(`/api/portfolios/portfolio/${portfolioItemId}`);
    return res.data;
  },
 
  // Freelancer statistics (from ProposalController)
  getFreelancerStats: async (freelancerId) => {
    const res = await api.get(`/api/freelancers/${freelancerId}/stats`);
    return res.data;
  }
};
 
export default freelancerService;