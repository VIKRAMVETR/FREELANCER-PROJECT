import React, { useState, useEffect } from "react";
import { useParams, useSearchParams } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import freelancerService from "../../services/freelancerService";
import ratingService from "../../services/ratingService";
import Loader from "../common/Loader";
 
const RateFreelancer = () => {
  const { freelancerId } = useParams();
  const { user } = useAuth();
  const [searchParams] = useSearchParams();
 
  const projectId = searchParams.get("projectId");
 
  const [freelancer, setFreelancer] = useState(null);
  const [rating, setRating] = useState(1);
  const [comment, setComment] = useState("");
 
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
 
  // Fetch freelancer details
  useEffect(() => {
    const loadFreelancer = async () => {
      try {
        const data = await freelancerService.getFreelancerByUserId(freelancerId);
        setFreelancer(data);
      } catch (err) {
        console.error("Failed to load freelancer:", err);
        setError("Could not load freelancer details.");
      } finally {
        setLoading(false);
      }
    };
    loadFreelancer();
  }, [freelancerId]);
 
  // Submit rating
const handleSubmit = async (e) => {
  e.preventDefault();
  setError("");
  setSuccess("");
  setSubmitting(true);
 
  try {
    await ratingService.addRating(
      freelancerId,
      user.id,            // clientId
      projectId,          // projectId
      rating,             // rating
      comment             // review
    );
 
    setSuccess("Rating submitted successfully!");
    setComment("");
    setRating(1);
 
  } catch (err) {
    console.error("Rating submit error:", err);
 
    if (err.response?.status === 409) {
      // ⭐ Duplicate rating
      setError("You have already rated this freelancer for this project.");
    } 
    else if (err.response?.status === 400) {
      // ⭐ Bad input or validation issue
      setError(err.response.data.message || "Invalid rating request.");
    } 
    else if (err.response?.status === 403) {
      // ⭐ If user is not a client
      setError("Only clients can submit ratings.");
    } 
    else {
      setError("Failed to submit rating.");
    }
  } finally {
    setSubmitting(false);
  }
};
 
  if (loading) return <Loader />;
 
  return (
<div className="container mt-4">
<h2>Rate Freelancer</h2>
 
      {error && <div className="alert alert-danger mt-3">{error}</div>}
      {success && <div className="alert alert-success mt-3">{success}</div>}
 
      {/* Freelancer Info */}
<div className="card shadow-sm mt-4">
<div className="card-header bg-primary text-white">
<h5 className="mb-0">Freelancer Details</h5>
</div>
<div className="card-body">
<h5>{freelancer?.title}</h5>
<p className="text-muted">{freelancer?.bio}</p>
</div>
</div>
 
      {/* Rating Form */}
<form className="mt-4" onSubmit={handleSubmit}>
<div className="card shadow-sm">
<div className="card-body">
 
            <div className="mb-3">
<label className="form-label">Rating (1–5)</label>
<select
                className="form-select"
                value={rating}
                onChange={(e) => setRating(Number(e.target.value))}
>
<option value="1">1 Star</option>
<option value="2">2 Star</option>
<option value="3">3 Star</option>
<option value="4">4 Star</option>
<option value="5">5 Star</option>
</select>
</div>
 
            <div className="mb-3">
<label className="form-label">Feedback Comment</label>
<textarea
                className="form-control"
                rows="3"
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                placeholder="Write a short review..."
              />
</div>
 
            <button
              type="submit"
              className="btn btn-success w-100"
              disabled={submitting}
>
              {submitting ? "Submitting..." : "Submit Rating"}
</button>
 
          </div>
</div>
</form>
</div>
  );
};
 
export default RateFreelancer;