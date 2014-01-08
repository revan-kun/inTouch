package com.epam.lab.intouch.controller.member.like.state;

public class Context {
	private State state;
	private Integer rating;

	public Context() {
		state = new InitialState(this);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setLikedState() {
		state = new LikedState(this);
	}

	public void setDislikedState() {
		state = new DislikedState(this);
	}

	public void setInitialState() {
		state = new InitialState(this);
	}

	public void like() {
		state.like();
	}

	public void dislike() {
		state.dislike();
	}

	public void neutralize() {
		state.neutralize();
	}

}
