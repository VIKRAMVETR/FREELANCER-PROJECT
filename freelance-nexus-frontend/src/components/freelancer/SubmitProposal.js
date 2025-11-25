import React, { useState, useEffect, useCallback } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import projectService from "../../services/projectService";
import proposalService from "../../services/proposalService";
import freelancerService from "../../services/freelancerService";
import Loader from "../common/Loader";
 
const SubmitProposal = () => {
  const { projectId } = useParams();
  const { user } = useAuth();
  const navigate = useNavigate();
 
  const [project, setProject] = useState(null);
  const [freelancerId, setFreelancerId] = useState(null);
 
  const [proposal, setProposal] = useState({
    proposedBudget: "",
    durationDays: "",
    coverLetter: "",
  });
 
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState("");
 
  // ---------------- FETCH PROJECT ----------------
  const fetchProject = useCallback(async () => {
    try {
      const data = await projectService.getProjectById(projectId);
      setProject(data);
    } catch (err) {
      setError("Failed to load project details");
    } finally {
      setLoading(false);
    }
  }, [projectId]);
 
  // ---------------- FETCH FREELANCER PROFILE ----------------
  const fetchFreelancer = useCallback(async () => {
    try {
      const data = await freelancerService.getFreelancerByUserId(user.id);
      setFreelancerId(data.id); // Correct freelancerId
    } catch (err) {
      setError("Unable to load freelancer profile.");
    }
  }, [user.id]);
 
  useEffect(() => {
    fetchProject();
    fetchFreelancer();
  }, [fetchProject, fetchFreelancer]);
 
  // ---------------- SUBMIT PROPOSAL ----------------
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
 
    if (!freelancerId) {
      setError("Invalid freelancer account.");
      return;
    }
 
    if (proposal.coverLetter.length < 50) {
      setError("Cover letter must be at least 50 characters.");
      return;
    }
 
    setSubmitting(true);
 
    try {
      const payload = {
        freelancerId: freelancerId,
        coverLetter: proposal.coverLetter,
        proposedBudget: Number(proposal.proposedBudget),
        deliveryDays: Number(proposal.durationDays)
      };
 
      await proposalService.submitProposal(projectId, payload);
 
      navigate("/freelancer/proposals", {
        state: { message: "Proposal submitted successfully!" },
      });
 
    } catch (err) {
      setError(err.response?.data || "Failed to submit proposal");
    } finally {
      setSubmitting(false);
    }
  };
 
  if (loading) return <Loader />;
 
  return (
<div className="container mt-4">
<div className="row">
<div className="col-lg-8 mx-auto">
<h2 className="mb-4">Submit Proposal</h2>
 
          {error && (
<div className="alert alert-danger">

    {typeof error === "string" ? error : error.message || JSON.stringify(error)}
</div>

)}

 
 
          {/* ---------------- PROJECT DETAILS ---------------- */}
<div className="card shadow-sm mb-4">
<div className="card-header bg-primary text-white">
<h5 className="mb-0">Project Details</h5>
</div>
<div className="card-body">
<h4>{project?.title}</h4>
<p className="text-muted">{project?.description}</p>
 
              <div className="row mt-3">
<div className="col-md-6">
<strong>Budget:</strong> ₹{project?.budgetMin} - ₹{project?.budgetMax}
</div>
<div className="col-md-6">
<strong>Duration:</strong> {project?.durationDays} days
</div>
</div>
 
              <div className="mt-3">
<strong>Required Skills:</strong>
<div className="mt-2">
                  {project?.requiredSkills?.map((skill, i) => (
<span key={i} className="badge bg-secondary me-1 mb-1">
                      {skill}
</span>
                  ))}
</div>
</div>
</div>
</div>
 
          {/* ---------------- PROPOSAL FORM ---------------- */}
<form onSubmit={handleSubmit}>
<div className="card shadow-sm mb-4">
<div className="card-header bg-success text-white">
<h5 className="mb-0">Your Proposal</h5>
</div>
<div className="card-body">
 
                {/* Bid Amount */}
<div className="mb-3">
<label className="form-label">Proposed Budget (₹) *</label>
<input
                    type="number"
                    className="form-control"
                    value={proposal.proposedBudget}
                    onChange={(e) =>
                      setProposal({ ...proposal, proposedBudget: e.target.value })
                    }
                    min={project?.budgetMin}
                    max={project?.budgetMax}
                    required
                  />
</div>
 
                {/* Duration */}
<div className="mb-3">
<label className="form-label">Duration (Days) *</label>
<input
                    type="number"
                    className="form-control"
                    value={proposal.durationDays}
                    onChange={(e) =>
                      setProposal({ ...proposal, durationDays: e.target.value })
                    }
                    min="1"
                    required
                  />
</div>
 
                {/* Cover Letter */}
<div className="mb-3">
<label className="form-label">Cover Letter *</label>
<textarea
                    className="form-control"
                    rows="7"
                    value={proposal.coverLetter}
                    onChange={(e) =>
                      setProposal({ ...proposal, coverLetter: e.target.value })
                    }
                    placeholder="Explain why you’re the best fit (at least 50 characters)"
                    required
                  />
</div>
 
              </div>
</div>
 
            {/* Buttons */}
<div className="d-flex justify-content-end gap-3">
<button
                type="button"
                className="btn btn-outline-secondary"
                onClick={() => navigate(-1)}
>
                Cancel
</button>
 
              <button type="submit" className="btn btn-primary" disabled={submitting}>
                {submitting ? "Submitting..." : "Submit Proposal"}
</button>
</div>
 
          </form>
</div>
</div>
</div>
  );
};
 
export default SubmitProposal;