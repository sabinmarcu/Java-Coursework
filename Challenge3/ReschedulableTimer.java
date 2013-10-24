import java.util.*;

class ReschedulableTimer extends Timer {
  private Runnable task;
  private TimerTask timerTask;
  private Timer timer = new Timer();

  public void schedule(Runnable runnable, long delay) {
    task = runnable;
    timerTask = new TimerTask(){
		public void run() {
			task.run();
		}
	};
    timer.schedule(timerTask, 0, delay);
  }

  public void reschedule(long delay) {
    timerTask.cancel();
    timerTask = new TimerTask() {
		public void run() {
			task.run();
		}
	};
    timer.schedule(timerTask, 0, delay);
  }
}
