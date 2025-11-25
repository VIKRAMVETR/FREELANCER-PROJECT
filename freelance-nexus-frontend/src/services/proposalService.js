import api from "./api";
 
const proposalService = {
 
  getProposalById: async (proposalId) => {
    const res = await api.get(`/api/proposal/${proposalId}`);
    return res.data;
  },
 
  getProposalsByProject: async (projectId) => {
    const res = await api.get(`/api/projects/${projectId}/proposals`);
    return res.data;
  },
 
  getFreelancerProposals: async (freelancerId) => {
    const res = await api.get(`/api/proposals/freelancer/${freelancerId}`);
    return res.data;
  },
 
  submitProposal: async (projectId, payload) => {
    const res = await api.post(`/api/projects/${projectId}/proposals`, payload);
    return res.data;
  },
 
  withdrawProposal: async (proposalId) => {
    const res = await api.put(`/api/proposals/${proposalId}/withdraw`);
    return res.data;
  },
 
  acceptProposal: async (proposalId) => {
    const res = await api.put(`/api/proposals/${proposalId}/accept`);
    return res.data;
  },
 
  rejectProposal: async (proposalId) => {
    const res = await api.put(`/api/proposals/${proposalId}/reject`);
    return res.data;
  }
};
 
export default proposalService;