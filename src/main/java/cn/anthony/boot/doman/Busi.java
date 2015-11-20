package cn.anthony.boot.doman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "busi")
public class Busi extends GenericEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    private boolean active = true;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "busi")
    transient private List<BusiCode> codeList;
    transient private String activeDesc;
    transient private Map<Long, String> codeMap;

    public String getActiveDesc() {
	if (active)
	    return "开";
	else
	    return "关";
    }

    public List<BusiCode> getCodeList() {
	return codeList;
    }

    public Map<Long, String> getCodeMap() {
	Map<Long, String> m = new HashMap<Long, String>();
	for (BusiCode bc : getCodeList())
	    m.put(bc.getId(), bc.getCode());
	return m;
    }

    public void setCodeList(List<BusiCode> codeList) {
	this.codeList = codeList;
    }

    public Busi(String title, String description) {
	super();
	this.name = title;
	this.description = description;
    }

    public Busi() {
	super();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

}
