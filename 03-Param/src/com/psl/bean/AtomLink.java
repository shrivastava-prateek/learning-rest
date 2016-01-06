package com.psl.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="link")
public class AtomLink {

	private String href,rel,type;

	public AtomLink() {
		super();
	}

	
	public AtomLink(String href, String rel, String type) {
		super();
		this.href = href;
		this.rel = rel;
		this.type = type;
	}

@XmlAttribute
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	@XmlAttribute
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
