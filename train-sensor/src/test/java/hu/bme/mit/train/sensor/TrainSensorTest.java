package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    private TrainController controller = mock(TrainController.class);
    private TrainUser user = mock(TrainUser.class);
    private TrainSensor sensor = new TrainSensorImpl(controller, user);

    @Test
    public void BelowAbsoluteThreshold() {
        sensor.overrideSpeedLimit(-1);
        verify(user).setAlarmState(true);
    }

    @Test
    public void AboveAbsoluteThreshold() {
        sensor.overrideSpeedLimit(501);
        verify(user).setAlarmState(true);
    }

    @Test
    public void BelowRelativeThreshold() {
        when(controller.getReferenceSpeed()).thenReturn(150);

        sensor.overrideSpeedLimit(50);
        verify(user).setAlarmState(true);
    }

    @Test
    public void NoAlarm() {
        when(controller.getReferenceSpeed()).thenReturn(150);

        sensor.overrideSpeedLimit(110);
        verify(user).setAlarmState(false);
    }
}
