package edu.csula.models;

public class Generator {
	private int id;
	private String name;
	private String description;
	private int rate;
	private int baseCost;
	private int unlockAt;

	public Generator(int id, String name, String description, int rate, int baseCost, int unlockAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rate = rate;
		this.baseCost = baseCost;
		this.unlockAt = unlockAt;
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

	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(int baseCost) {
		this.baseCost = baseCost;
	}

	public int getUnlockAt() {
		return unlockAt;
	}
	public void setUnlockAt(int unlockAt) {
		this.unlockAt = unlockAt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final Generator generator = (Generator) obj;
		return this.id == generator.id && this.name.equals(generator.name) &&
			this.description.equals(generator.description) &&
			this.baseCost == generator.baseCost &&
			this.rate == generator.rate &&
			this.unlockAt == generator.unlockAt;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
