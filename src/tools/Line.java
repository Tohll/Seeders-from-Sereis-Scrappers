package tools;

import java.awt.Point;

public class Line {

	private final Point begin;
	private final Point end;

	public Line(final Point begin, final Point end) {
		this.begin = begin;
		this.end = end;
	}

	public Point getBegin() {
		return this.begin;
	}

	public Point getEnd() {
		return this.end;
	}

}
