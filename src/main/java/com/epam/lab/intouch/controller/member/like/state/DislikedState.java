package com.epam.lab.intouch.controller.member.like.state;

public class DislikedState implements State {
	private Context context;

	public DislikedState(Context context) {
		this.context = context;
	}

	@Override
	public void like() {
		Integer likedRating = context.getRating() + 2;
		context.setRating(likedRating);
		context.setLikedState();
	}

	@Override
	public void dislike() {

	}

	@Override
	public void neutralize() {
		Integer likedRating = context.getRating() + 1;
		context.setRating(likedRating);
		context.setInitialState();
	}

}
