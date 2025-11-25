import React, { useState, useEffect, useCallback } from "react";
import { useAuth } from "../../context/AuthContext";
import { useParams } from "react-router-dom";
import freelancerService from "../../services/freelancerService";
import Loader from "../common/Loader";

const FreelancerProfile = () => {
  const { user } = useAuth();
  const { freelancerId } = useParams();

  // Determine whose profile to load
  const effectiveId = freelancerId || user?.id;

  // Detect if a CLIENT is viewing someone else's profile
  const isClientViewing = !!freelancerId;

  const [profile, setProfile] = useState({
    title: "",
    bio: "",
    hourlyRate: "",
    skills: [],
    portfolio: []
  });

  const [newSkill, setNewSkill] = useState({
    skillName: "",
    proficiencyLevel: "BEGINNER",
    yearsExperience: 1
  });

  const [newPortfolio, setNewPortfolio] = useState({
    title: "",
    description: "",
    projectUrl: ""
  });

  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState({ type: "", text: "" });

  const fetchProfile = useCallback(async () => {
    try {
      const data = await freelancerService.getFreelancerProfile(effectiveId);
      const portfolios = await freelancerService.getFreelancerPortfolio(effectiveId);

      setProfile({
        title: data.title || "",
        bio: data.bio || "",
        hourlyRate: data.hourlyRate || "",
        skills: data.skills || [],
        portfolio: portfolios || []
      });

    } catch (error) {
      console.error("Error fetching profile:", error);
    } finally {
      setLoading(false);
    }
  }, [effectiveId]);

  useEffect(() => {
    if (effectiveId) {
      fetchProfile();
    }
  }, [fetchProfile, effectiveId]);

  // Save profile (Freelancer only)
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isClientViewing) return; // prevent clients from saving

    setSaving(true);

    try {
      const payload = {
        ...profile,
        userId: user.id,
        title: profile.title || "Freelancer"
      };

      await freelancerService.updateFreelancerProfile(user.id, payload);

      setMessage({ type: "success", text: "Profile updated successfully!" });
    } catch (error) {
      setMessage({ type: "danger", text: "Failed to update profile" });
    } finally {
      setSaving(false);
    }
  };

  // Add Skill
  const addSkill = () => {
    if (isClientViewing) return;
    if (!newSkill.skillName.trim()) return;

    setProfile((prev) => ({
      ...prev,
      skills: [...prev.skills, { ...newSkill }]
    }));

    setNewSkill({
      skillName: "",
      proficiencyLevel: "BEGINNER",
      yearsExperience: 1
    });
  };

  const removeSkill = (id) => {
    if (isClientViewing) return;
    setProfile((prev) => ({
      ...prev,
      skills: prev.skills.filter((s) => s.id !== id)
    }));
  };

  // Portfolio
  const addPortfolioItem = async () => {
    if (isClientViewing) return;
    if (!newPortfolio.title || !newPortfolio.description) return;

    try {
      const saved = await freelancerService.addPortfolioItem(user.id, newPortfolio);

      setProfile((prev) => ({
        ...prev,
        portfolio: [...prev.portfolio, saved]
      }));

      setNewPortfolio({ title: "", description: "", projectUrl: "" });
    } catch (e) {
      console.log("Portfolio add failed:", e);
    }
  };

  const removePortfolioItem = async (id) => {
    if (isClientViewing) return;

    try {
      await freelancerService.deletePortfolioItem(id);

      setProfile((prev) => ({
        ...prev,
        portfolio: prev.portfolio.filter((item) => item.id !== id)
      }));
    } catch (e) {
      console.log("Delete failed:", e);
    }
  };

  if (loading) return <Loader />;

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-lg-8 mx-auto">
          <h2 className="mb-4">
            {isClientViewing ? "Freelancer Profile" : "My Profile"}
          </h2>

          {message.text && (
            <div className={`alert alert-${message.type}`}>{message.text}</div>
          )}

          <form onSubmit={handleSubmit}>
            {/* BASIC INFO */}
            <div className="card shadow-sm mb-4">
              <div className="card-header bg-primary text-white">
                <h5 className="mb-0">Basic Information</h5>
              </div>

              <div className="card-body">
                <div className="mb-3">
                  <label className="form-label">Profile Title</label>
                  <input
                    type="text"
                    className="form-control"
                    disabled={isClientViewing}
                    value={profile.title}
                    onChange={(e) =>
                      setProfile({ ...profile, title: e.target.value })
                    }
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">Bio</label>
                  <textarea
                    className="form-control"
                    rows="4"
                    disabled={isClientViewing}
                    value={profile.bio}
                    onChange={(e) =>
                      setProfile({ ...profile, bio: e.target.value })
                    }
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">Hourly Rate (₹)</label>
                  <input
                    type="number"
                    className="form-control"
                    disabled={isClientViewing}
                    value={profile.hourlyRate}
                    onChange={(e) =>
                      setProfile({ ...profile, hourlyRate: e.target.value })
                    }
                  />
                </div>
              </div>
            </div>

            {/* SKILLS */}
            <div className="card shadow-sm mb-4">
              <div className="card-header bg-success text-white">
                <h5 className="mb-0">Skills</h5>
              </div>

              <div className="card-body">
                {!isClientViewing && (
                  <div className="row g-2 align-items-end">
                    <div className="col-md-4">
                      <input
                        type="text"
                        className="form-control"
                        value={newSkill.skillName}
                        onChange={(e) =>
                          setNewSkill({ ...newSkill, skillName: e.target.value })
                        }
                      />
                    </div>

                    <div className="col-md-4">
                      <select
                        className="form-select"
                        value={newSkill.proficiencyLevel}
                        onChange={(e) =>
                          setNewSkill({
                            ...newSkill,
                            proficiencyLevel: e.target.value
                          })
                        }
                      >
                        <option value="BEGINNER">Beginner</option>
                        <option value="INTERMEDIATE">Intermediate</option>
                        <option value="ADVANCED">Advanced</option>
                        <option value="EXPERT">Expert</option>
                      </select>
                    </div>

                    <div className="col-md-3">
                      <input
                        type="number"
                        className="form-control"
                        min="0"
                        max="50"
                        value={newSkill.yearsExperience}
                        onChange={(e) =>
                          setNewSkill({
                            ...newSkill,
                            yearsExperience: Number(e.target.value)
                          })
                        }
                      />
                    </div>

                    <div className="col-md-1">
                      <button
                        type="button"
                        className="btn btn-success w-100"
                        onClick={addSkill}
                      >
                        +
                      </button>
                    </div>
                  </div>
                )}

                <div className="mt-3">
                  {profile.skills.map((skill) => (
                    <div
                      key={skill.id || skill.skillName}
                      className="d-flex justify-content-between p-2 border rounded mb-2"
                    >
                      <div>
                        <strong>{skill.skillName}</strong> —{" "}
                        {skill.proficiencyLevel} ({skill.yearsExperience} yrs)
                      </div>

                      {!isClientViewing && (
                        <button
                          type="button"
                          className="btn btn-sm btn-outline-danger"
                          onClick={() => removeSkill(skill.id)}
                        >
                          Remove
                        </button>
                      )}
                    </div>
                  ))}
                </div>
              </div>
            </div>

            {/* PORTFOLIO */}
            <div className="card shadow-sm mb-4">
              <div className="card-header bg-info text-white">
                <h5 className="mb-0">Portfolio</h5>
              </div>

              <div className="card-body">
                {!isClientViewing && (
                  <div className="row mb-3">
                    <div className="col-md-6">
                      <input
                        type="text"
                        className="form-control mb-2"
                        value={newPortfolio.title}
                        onChange={(e) =>
                          setNewPortfolio({ ...newPortfolio, title: e.target.value })
                        }
                        placeholder="Project Title"
                      />
                    </div>

                    <div className="col-md-6">
                      <input
                        type="url"
                        className="form-control mb-2"
                        value={newPortfolio.projectUrl}
                        onChange={(e) =>
                          setNewPortfolio({ ...newPortfolio, projectUrl: e.target.value })
                        }
                        placeholder="Project URL"
                      />
                    </div>

                    <div className="col-12">
                      <textarea
                        className="form-control mb-2"
                        rows="2"
                        value={newPortfolio.description}
                        onChange={(e) =>
                          setNewPortfolio({
                            ...newPortfolio,
                            description: e.target.value
                          })
                        }
                        placeholder="Project Description"
                      />
                    </div>

                    <div className="col-12">
                      <button
                        type="button"
                        className="btn btn-info"
                        onClick={addPortfolioItem}
                      >
                        Add Portfolio Item
                      </button>
                    </div>
                  </div>
                )}

                {profile.portfolio.map((item) => (
                  <div
                    key={item.id}
                    className="portfolio-item d-flex justify-content-between"
                  >
                    <div>
                      <h6>{item.title}</h6>
                      <p className="text-muted mb-1">{item.description}</p>
                      {item.projectUrl && (
                        <a href={item.projectUrl} target="_blank" rel="noreferrer">
                          View Project →
                        </a>
                      )}
                    </div>

                    {!isClientViewing && (
                      <button
                        type="button"
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => removePortfolioItem(item.id)}
                      >
                        Remove
                      </button>
                    )}
                  </div>
                ))}
              </div>
            </div>

            {!isClientViewing && (
              <button
                type="submit"
                className="btn btn-primary w-100"
                disabled={saving}
              >
                {saving ? "Saving..." : "Save Profile"}
              </button>
            )}
          </form>
        </div>
      </div>
    </div>
  );
};

export default FreelancerProfile;