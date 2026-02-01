package LLD;

/**
 * PRACTICE QUESTION: Media Player State Management
 * 
 * Scenario:
 * You are building a logic for a Media Player.
 * The player has three states:
 * 1. StoppedState (Initial State)
 * 2. PlayingState
 * 3. PausedState
 * 
 * The player has three buttons (actions):
 * - pressPlay()
 * - pressPause()
 * - pressStop()
 * 
 * Behavior:
 * 
 * 1. IF Stopped:
 * - pressPlay() -> Starts playing (Transition to PlayingState)
 * - pressPause() -> Prints "Cannot pause. Player is stopped."
 * - pressStop() -> Prints "Already stopped."
 * 
 * 2. IF Playing:
 * - pressPlay() -> Prints "Already playing."
 * - pressPause() -> Pauses video (Transition to PausedState)
 * - pressStop() -> Stops video (Transition to StoppedState)
 * 
 * 3. IF Paused:
 * - pressPlay() -> Resumes playing (Transition to PlayingState)
 * - pressPause() -> Prints "Already paused."
 * - pressStop() -> Stops video (Transition to StoppedState)
 */

// 1. Define Interface
interface State {
    void pressPlay(MediaPlayer player);

    void pressPause(MediaPlayer player);

    void pressStop(MediaPlayer player);
}

// 2. Concrete States

// STOPPED STATE
class StoppedState implements State {
    @Override
    public void pressPlay(MediaPlayer player) {
        System.out.println("Starts playing.");
        player.setState(new PlayingState());
    }

    @Override
    public void pressPause(MediaPlayer player) {
        System.out.println("Cannot pause. Player is stopped.");
    }

    @Override
    public void pressStop(MediaPlayer player) {
        System.out.println("Already stopped.");
    }
}

// PLAYING STATE
class PlayingState implements State {
    @Override
    public void pressPlay(MediaPlayer player) {
        System.out.println("Already playing.");
    }

    @Override
    public void pressPause(MediaPlayer player) {
        System.out.println("Pauses video.");
        player.setState(new PausedState());
    }

    @Override
    public void pressStop(MediaPlayer player) {
        System.out.println("Stops video.");
        player.setState(new StoppedState());
    }
}

// PAUSED STATE
class PausedState implements State {
    @Override
    public void pressPlay(MediaPlayer player) {
        System.out.println("Resumes playing.");
        player.setState(new PlayingState());
    }

    @Override
    public void pressPause(MediaPlayer player) {
        System.out.println("Already paused.");
    }

    @Override
    public void pressStop(MediaPlayer player) {
        System.out.println("Stops video.");
        player.setState(new StoppedState());
    }
}

// 3. Context Class
class MediaPlayer {
    private State currentState;

    public MediaPlayer() {
        // Initial state is Stopped
        this.currentState = new StoppedState();
        System.out.println("Media Player initialized.");
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void pressPlay() {
        currentState.pressPlay(this);
    }

    public void pressPause() {
        currentState.pressPause(this);
    }

    public void pressStop() {
        currentState.pressStop(this);
    }
}

public class StatePattern_Practice {
    public static void main(String[] args) {
        System.out.println("--- Media Player State Pattern Practice ---");

        MediaPlayer player = new MediaPlayer();

        // Testing implementation

        System.out.println("\nAction: Press Play");
        // Should start Playing
        player.pressPlay();

        System.out.println("\nAction: Press Pause");
        // Should Pause
        player.pressPause();

        System.out.println("\nAction: Press Play");
        // Should Resume Playing
        player.pressPlay();

        System.out.println("\nAction: Press Stop");
        // Should Stop
        player.pressStop();

        System.out.println("\nAction: Press Pause");
        // Should fail to pause
        player.pressPause();
    }
}
