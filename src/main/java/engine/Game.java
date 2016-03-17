package engine;

public abstract class Game implements Engine.Callbacks {

	private Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
