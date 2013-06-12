package com.epam.lab.intouch.controller.member.like.state;

public class InitialState implements State {
	private Context context;

	public InitialState(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void like() {
		Integer likedRating = context.getRating() + 1;
		context.setRating(likedRating);
		context.setLikedState();
	}

	@Override
	public void dislike() {
		Integer dislikedRating = context.getRating() - 1;
		context.setRating(dislikedRating);
		context.setDislikedState();
	}

	@Override
	public void neutralize() {

	}

}
