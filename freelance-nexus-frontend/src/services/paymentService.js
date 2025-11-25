import api from './api';

const paymentService = {
  initiatePayment: async (paymentData) => {
    const response = await api.post('/api/payments/initiate', paymentData);
    return response.data;
  },

  processUPIPayment: async (paymentId, upiData) => {
    const response = await api.post(`/api/payments/${paymentId}/upi`, upiData);
    return response.data;
  },

  getPaymentById: async (paymentId) => {
    const response = await api.get(`/api/payments/${paymentId}`);
    return response.data;
  },

  getPaymentHistory: async (userId) => {
    const response = await api.get(`/api/payments/user/${userId}`);
    return response.data;
  },

  getPaymentByProposal: async (proposalId) => {
    const response = await api.get(`/api/payments/proposal/${proposalId}`);
    return response.data;
  },

  verifyPayment: async (transactionId) => {
  const response = await api.post(`/api/payments/verify/${transactionId}`);
  return response.data;
},

  requestRefund: async (paymentId, reason) => {
    const response = await api.post(`/api/payments/${paymentId}/refund`, { reason });
    return response.data;
  }
};

export default paymentService;
