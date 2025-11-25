import api from "./api";
 
const ratingService = {
  addRating: async (freelancerId, clientId, projectId, rating, review) => {
    const res = await api.post(`/api/ratings/${freelancerId}/ratings`, {
      clientId,
      projectId,
      rating,
      review
    });
 
    return res.data;
  },
};
 
export default ratingService;