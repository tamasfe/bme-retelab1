package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);

		this.checkAlarm();
	}

	private void checkAlarm() {
		// absolute margin
		if (speedLimit < 0 || speedLimit > 500) {
			this.user.setAlarmState(true);
			return;
		}

		// relative margin
		if (this.controller.getReferenceSpeed() != 0
				&& (double) speedLimit / ((double) this.controller.getReferenceSpeed()) < 0.5) {
			this.user.setAlarmState(true);
			return;
		}

		this.user.setAlarmState(false);
	}
}
