package com.islington.model;

import java.time.LocalDate;

public class FeedbackModel {
	private int feedbackId;
	private int userId;
	private LocalDate feedbackDate;
	private String feedbackDescription;
	public FeedbackModel() {
	}

	public FeedbackModel(int feedbackId, int userId,  LocalDate feedbackDate,
			String feedbackDescription) {
		super();
		this.feedbackId = feedbackId;
		this.userId = userId;
		this.feedbackDate = feedbackDate;
		this.feedbackDescription = feedbackDescription;

	}

	/**
	 * @return the feedbackId
	 */
	public int getFeedbackId() {
		return feedbackId;
	}

	/**
	 * @param feedbackId the feedbackId to set
	 */
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackDescription() {
		return feedbackDescription;
	}

	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}
}
