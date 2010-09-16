package com.linkage.app.gqt.backstage.sysparam.entity;

public class Sysparam implements java.io.Serializable {

	// Fields

	private Long paramid;

	private String paramcode;

	private String paramkey;

	private String paramvalue;

	private Long paramstate;

	private Long paramorder;

	private String paramdesc;

	private String paramremark;

	// Constructors

	/** default constructor */
	public Sysparam() {
	}

	/** minimal constructor */
	public Sysparam(Long paramid, String paramcode, String paramkey,
			String paramvalue, Long paramstate) {
		this.paramid = paramid;
		this.paramcode = paramcode;
		this.paramkey = paramkey;
		this.paramvalue = paramvalue;
		this.paramstate = paramstate;
	}

	/** full constructor */
	public Sysparam(Long paramid, String paramcode, String paramkey,
			String paramvalue, Long paramstate, Long paramorder,
			String paramdesc, String paramremark) {
		this.paramid = paramid;
		this.paramcode = paramcode;
		this.paramkey = paramkey;
		this.paramvalue = paramvalue;
		this.paramstate = paramstate;
		this.paramorder = paramorder;
		this.paramdesc = paramdesc;
		this.paramremark = paramremark;
	}

	// Property accessors

	public Long getParamid() {
		return this.paramid;
	}

	public void setParamid(Long paramid) {
		this.paramid = paramid;
	}

	public String getParamcode() {
		return this.paramcode;
	}

	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}

	public String getParamkey() {
		return this.paramkey;
	}

	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}

	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	public Long getParamstate() {
		return this.paramstate;
	}

	public void setParamstate(Long paramstate) {
		this.paramstate = paramstate;
	}

	public Long getParamorder() {
		return this.paramorder;
	}

	public void setParamorder(Long paramorder) {
		this.paramorder = paramorder;
	}

	public String getParamdesc() {
		return this.paramdesc;
	}

	public void setParamdesc(String paramdesc) {
		this.paramdesc = paramdesc;
	}

	public String getParamremark() {
		return this.paramremark;
	}

	public void setParamremark(String paramremark) {
		this.paramremark = paramremark;
	}
	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Sysparam))
			return false;
		Sysparam castOther = (Sysparam) other;

		return ((this.getParamid() == castOther.getParamid()) || (this
				.getParamid() != null
				&& castOther.getParamid() != null && this.getParamid().equals(
				castOther.getParamid())))
				&& ((this.getParamcode() == castOther.getParamcode()) || (this
						.getParamcode() != null
						&& castOther.getParamcode() != null && this
						.getParamcode().equals(castOther.getParamcode())))
				&& ((this.getParamkey() == castOther.getParamkey()) || (this
						.getParamkey() != null
						&& castOther.getParamkey() != null && this
						.getParamkey().equals(castOther.getParamkey())))
				&& ((this.getParamvalue() == castOther.getParamvalue()) || (this
						.getParamvalue() != null
						&& castOther.getParamvalue() != null && this
						.getParamvalue().equals(castOther.getParamvalue())))
				&& ((this.getParamstate() == castOther.getParamstate()) || (this
						.getParamstate() != null
						&& castOther.getParamstate() != null && this
						.getParamstate().equals(castOther.getParamstate())))
				&& ((this.getParamorder() == castOther.getParamorder()) || (this
						.getParamorder() != null
						&& castOther.getParamorder() != null && this
						.getParamorder().equals(castOther.getParamorder())))
				&& ((this.getParamdesc() == castOther.getParamdesc()) || (this
						.getParamdesc() != null
						&& castOther.getParamdesc() != null && this
						.getParamdesc().equals(castOther.getParamdesc())))
				&& ((this.getParamremark() == castOther.getParamremark()) || (this
						.getParamremark() != null
						&& castOther.getParamremark() != null && this
						.getParamremark().equals(castOther.getParamremark())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getParamid() == null ? 0 : this.getParamid().hashCode());
		result = 37 * result
				+ (getParamcode() == null ? 0 : this.getParamcode().hashCode());
		result = 37 * result
				+ (getParamkey() == null ? 0 : this.getParamkey().hashCode());
		result = 37
				* result
				+ (getParamvalue() == null ? 0 : this.getParamvalue()
						.hashCode());
		result = 37
				* result
				+ (getParamstate() == null ? 0 : this.getParamstate()
						.hashCode());
		result = 37
				* result
				+ (getParamorder() == null ? 0 : this.getParamorder()
						.hashCode());
		result = 37 * result
				+ (getParamdesc() == null ? 0 : this.getParamdesc().hashCode());
		result = 37
				* result
				+ (getParamremark() == null ? 0 : this.getParamremark()
						.hashCode());
		return result;
	}

}