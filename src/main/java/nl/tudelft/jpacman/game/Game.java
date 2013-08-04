package nl.tudelft.jpacman.game;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.model.Character;
import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.NonPlayerCharacter;

/**
 * High level class that handles a complete game, i.e. the player input, Ghost
 * AIs, the Level, etc.
 * 
 * @author Jeroen Roosen
 */
public abstract class Game {

	/**
	 * The scheduler that will time the AI movements.
	 */
	private ScheduledExecutorService scheduler;

	/**
	 * Whether this game is currently in progress.
	 */
	private boolean inProgress;

	/**
	 * Create a new game.
	 * 
	 * @param ghostController
	 *            The controller moving the ghosts on the level around.
	 */
	public Game() {
		this.inProgress = false;
	}

	/**
	 * Starts or resumes this game.
	 */
	public void start() {
		inProgress = true;
		startScheduler();
		startGhosts();
	}

	/**
	 * Stops or pauses this game.
	 */
	public void stop() {
		stopGhosts();
		inProgress = false;
	}

	/**
	 * Returns whether or not this game is in progress.
	 * 
	 * @return <code>true</code> iff the game is currently in progress.
	 */
	public boolean isInProgress() {
		return inProgress;
	}

	/**
	 * Moves the Pac-Man one square in the given direction and calculates the
	 * new game state.
	 * 
	 * @param pacMan
	 *            The Pac-Man to move.
	 * @param direction
	 *            The direction to move towards.
	 */
	public void move(Character pacMan, Direction direction) {
		if (isInProgress()) {
			getCurrentLevel().move(pacMan, direction);

			if (getCurrentLevel().lost() || getCurrentLevel().completed()) {
				stop();
			}
		}
	}

	/**
	 * @return The level that is currently being played.
	 */
	public abstract Level getCurrentLevel();

	/**
	 * (Re)starts the scheduler for the NPC movements.
	 */
	private void startScheduler() {
		if (!(scheduler == null || scheduler.isShutdown() || scheduler.isTerminated())) {
			scheduler.shutdownNow();
		}
		scheduler = Executors.newSingleThreadScheduledExecutor();
	}
	
	/**
	 * Starts the scheduling for the ghosts.
	 */
	private void startGhosts() {
		Collection<Ghost> ghosts = getCurrentLevel().getGhosts();
		for (Ghost g : ghosts) {
			scheduleNpcMove(g);
		}
	}

	/**
	 * Stops the scheduling of ghost movements.
	 */
	private void stopGhosts() {
		scheduler.shutdownNow();
	}

	/**
	 * Schedules the next move for an NPC.
	 * 
	 * @param npc
	 *            The NPC to move.
	 */
	private void scheduleNpcMove(NonPlayerCharacter npc) {
		if (isInProgress()) {
			scheduler.schedule(new ScheduledMove(npc), npc.getDelay(),
					TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * Scheduled task that will determine and execute the next move for an NPC.
	 * Reschedules itself after execution.
	 * 
	 * @author Jeroen Roosen
	 */
	private class ScheduledMove implements Runnable {

		/**
		 * The NPC to move when this task is executed.
		 */
		private final NonPlayerCharacter subject;

		/**
		 * Create a new move task.
		 * 
		 * @param npc
		 *            The NPC to move upon execution.
		 */
		private ScheduledMove(NonPlayerCharacter npc) {
			this.subject = npc;
		}

		@Override
		public void run() {
			Direction move = subject.nextMove();
			if (move != null) {
				move(subject, move);
			}
			if (isInProgress()) {
				scheduler.schedule(this, subject.getDelay(),
						TimeUnit.MILLISECONDS);
			}
		}
	}
}
