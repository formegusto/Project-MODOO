package com.modoo.wrk.mdf;

import java.util.Date;

public class MFDTransferVO {
	private int seq;
	private int bseq;
	private String requser;
	private String resuser;
	private String reqmsg;
	private String resmsg;
	private String status;
	private Date createAt;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public String getRequser() {
		return requser;
	}
	public void setRequser(String requser) {
		this.requser = requser;
	}
	public String getResuser() {
		return resuser;
	}
	public void setResuser(String resuser) {
		this.resuser = resuser;
	}
	public String getReqmsg() {
		return reqmsg;
	}
	public void setReqmsg(String reqmsg) {
		this.reqmsg = reqmsg;
	}
	public String getResmsg() {
		return resmsg;
	}
	public void setResmsg(String resmsg) {
		this.resmsg = resmsg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@Override
	public String toString() {
		return "MFDTransferVO [seq=" + seq + ", bseq=" + bseq + ", requser=" + requser + ", resuser=" + resuser
				+ ", reqmsg=" + reqmsg + ", resmsg=" + resmsg + ", status=" + status + ", createAt=" + createAt + "]";
	}
}
