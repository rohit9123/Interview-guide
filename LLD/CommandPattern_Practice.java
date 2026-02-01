package LLD;

/**
 * PRACTICE QUESTION: Smart Home Remote Control
 * 
 * Scenario:
 * You are building a Remote Control for a Smart Home.
 * The remote has one programmable button.
 * 
 * We have two devices (Receivers):
 * 1. `TV` (Methods: turnOn, turnOff)
 * 2. `Stereo` (Methods: startMusic, stopMusic)
 * 
 * Your task is to implement the Command Pattern so that the Remote
 * can be configured to control either the TV or the Stereo.
 * 
 * INSTRUCTIONS:
 * 1. Create a `Command` interface with an `execute()` method.
 * 2. Implement `TurnTvOnCommand`: calls `tv.turnOn()`.
 * 3. Implement `TurnStereoOnCommand`: calls `stereo.startMusic()`.
 * 4. Implement the `RemoteControl` (Invoker) which holds a command and has a
 * `pressButton()` method.
 * 5. Test in Main by:
 * - Creating a TV and binding the Remote to turn it on.
 * - Creating a Stereo and changing the Remote to turn it on.
 */

// 1. Command Interface
interface Command {
    void execute();
}

// RECERIVERS (Already provided)
class TV {
    void turnOn() {
        System.out.println("TV is now ON.");
    }

    void turnOff() {
        System.out.println("TV is now OFF.");
    }
}

class Stereo {
    void startMusic() {
        System.out.println("Stereo is playing music.");
    }

    void stopMusic() {
        System.out.println("Stereo is stopped.");
    }
}

// 2. Concrete Commands

// TODO: Implement TurnTvOnCommand
// class TurnTvOnCommand ...

// TODO: Implement TurnStereoOnCommand
// class TurnStereoOnCommand ...

// 3. Invoker (Remote)
class RemoteControl {
    // TODO: Add a field for Command
    // Command command;

    public void setCommand(Command command) {
        // TODO: Assign command
    }

    public void pressButton() {
        // TODO: Execute command
        System.out.println("Button pressed...");
        // command.execute();
    }
}

public class CommandPattern_Practice {
    public static void main(String[] args) {
        System.out.println("--- Command Pattern Practice ---");

        // 1. Create Receivers
        TV myTv = new TV();
        Stereo myStereo = new Stereo();

        // 2. Create Invoker
        RemoteControl remote = new RemoteControl();

        // 3. Test TV Command
        // Command tvOn = new TurnTvOnCommand(myTv);
        // remote.setCommand(tvOn);
        // remote.pressButton();

        // 4. Test Stereo Command
        // Command stereoOn = new TurnStereoOnCommand(myStereo);
        // remote.setCommand(stereoOn);
        // remote.pressButton();
    }
}
