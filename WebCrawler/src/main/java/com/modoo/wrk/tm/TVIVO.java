package com.modoo.wrk.tm;

public class TVIVO {
	private int tviseq;
	private int tseq;
	private int positive;
	private int negative;
	private int neutral;
	
	public int getTviseq() {
		return tviseq;
	}
	public void setTviseq(int tviseq) {
		this.tviseq = tviseq;
	}
	public int getTseq() {
		return tseq;
	}
	public void setTseq(int tseq) {
		this.tseq = tseq;
	}
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getNegative() {
		return negative;
	}
	public void setNegative(int negative) {
		this.negative = negative;
	}
	public int getNeutral() {
		return neutral;
	}
	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}
	
	@Override
	public String toString() {
		return "TVIVO [tviseq=" + tviseq + ", tseq=" + tseq + ", positive=" + positive + ", negative=" + negative
				+ ", neutral=" + neutral + "]";
	}
}
