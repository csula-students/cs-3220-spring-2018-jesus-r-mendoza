package edu.csula.models;

public class Event {
	private int id;
	private String name;
	private String description;
	private int triggerAt;

	public Event(int id, String name, String description, int triggerAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.triggerAt = triggerAt;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public int getTriggerAt() {
		return triggerAt;
	}
	public void setTriggerAt(int triggerAt) {
		this.triggerAt = triggerAt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final Event event = (Event) obj;
		return this.id == event.id && this.name.equals(event.name) &&
			this.description.equals(event.description) &&
			this.triggerAt == event.triggerAt;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
