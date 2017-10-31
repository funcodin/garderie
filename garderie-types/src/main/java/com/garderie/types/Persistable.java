/**
 * 
 */
package com.garderie.types;

import java.util.Date;

/**
 * @author Robot
 *
 */
public interface Persistable {
	public String getId();

	public void setId(final String id);

	public Date getCreatedDate();

	public void setCreatedDate(final Date createdDate);

	public Date getModifiedDate();

	public void setModifiedDate(final Date modifiedDate);

}
