package com.epam.lab.intouch.controller.member.like.state;

public class LikedState implements State {
	private Context context;

	public LikedState(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void like() {

	}

	@Override
	public void dislike() {
		Integer dislikedRating = context.getRating() - 2;
		context.setRating(dislikedRating);
		context.setDislikedState();
	}

	@Override
	public void neutralize() {
		Integer dislikedRating = context.getRating() - 1;
		context.setRating(dislikedRating);
		context.setInitialState();
	}

}
