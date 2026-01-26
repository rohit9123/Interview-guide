# Observer Design Pattern

**Type**: Behavioral Pattern

## 1. What is the Observer Pattern?

The Observer Pattern defines a subscription mechanism to notify multiple objects about any events that happen to the object they're observing. It defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

**Core Idea**: "Don't call us, we'll call you." instead of repeatedly asking "Are you done yet?" (polling), the observer waits to be told when something interesting happens.

## 2. When to use it?

*   **One-to-Many Dependency**: When a change to one object requires changing others, and you don't know how many objects need to be changed.
*   **Decoupling**: When some objects in your app need to observe others, but only for a limited time or in specific cases.
*   **Event Management**: When you need to create a chain reaction. For example, clicking a button (Subject) triggers multiple actions (Observers) like playing a sound, updating the UI, and logging the click.

## 3. Real-World Analogy

**Youtube Channel / Newsletter**:
1.  **Subject (Publisher)**: A Youtube Channel or a Newspaper (e.g., "Tech Daily").
2.  **Observer (Subscriber)**: Users who want to receive updates.

*   You subscribe to a channel.
*   You don't go to the channel page every 5 minutes to check for new videos (Polling).
*   Instead, when a new video is uploaded, Youtube **notifies** you (Push).
*   If you unsubscribe, you stop getting notifications.

## 4. Structure (UML Concept)

1.  **Subject (Publisher)**: Issues events of interest to other objects. It also maintains a subscription infrastructure that lets new subscribers join and current subscribers leave.
2.  **Observer (Subscriber)**: Declares the notification interface. In most cases, it consists of a single `update` method.
3.  **Concrete Subject**: Sends notifications to its observers when its state changes.
4.  **Concrete Observers**: Perform some actions in response to notifications issued by the publisher.

## 5. Java Example: Youtube Channel Notification System

Let's implement the Youtube analogy.

### Step 1: Observer Interface

```java
// The Observer
public interface Observer {
    void update(String channelName, String videoTitle);
}
```

### Step 2: Subject Interface (Optional but good practice)

```java
// The Subject
public interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(String videoTitle);
}
```

### Step 3: Concrete Subject (Youtube Channel)

```java
import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel implements Subject {
    private String channelName;
    private List<Observer> subscribers = new ArrayList<>();

    public YoutubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
        System.out.println("New subscriber added to " + channelName);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
        System.out.println("Subscriber removed from " + channelName);
    }

    @Override
    public void notifyObservers(String videoTitle) {
        for (Observer observer : subscribers) {
            observer.update(channelName, videoTitle);
        }
    }

    // Parameters triggering the notification
    public void uploadVideo(String videoTitle) {
        System.out.println("\nAssuming Channel '" + channelName + "' is processing video: " + videoTitle + "...");
        // ... processing logic ...
        notifyObservers(videoTitle);
    }
}
```

### Step 4: Concrete Observers (Subscribers)

```java
public class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String channelName, String videoTitle) {
        System.out.println("Hello " + name + ", " + channelName + " just uploaded: " + videoTitle);
    }
}
```

### Step 5: Client Code

```java
public class Main {
    public static void main(String[] args) {
        // Create the Channel (Subject)
        YoutubeChannel codeChannel = new YoutubeChannel("CodeWithRohit");

        // Create Subscribers (Observers)
        Subscriber sub1 = new Subscriber("Alice");
        Subscriber sub2 = new Subscriber("Bob");
        Subscriber sub3 = new Subscriber("Charlie");

        // Register Subscribers
        codeChannel.subscribe(sub1);
        codeChannel.subscribe(sub2);
        codeChannel.subscribe(sub3);

        // Upload a video (State Change) -> Triggers Notification
        codeChannel.uploadVideo("Observer Pattern in 5 Minutes");

        // Unsubscribe one user
        codeChannel.unsubscribe(sub2);

        // Upload another video
        codeChannel.uploadVideo("Strategy vs Observer Pattern");
    }
}
```

## 6. Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Open/Closed Principle**: You can introduce new subscriber classes without having to change the publisher's code (and vice versa if there's a publisher interface). | **Random Update Order**: Subscribers are notified in no specific order (usually). |
| **Runtime Relations**: You can establish relations between objects at runtime. | **Memory Leaks**: If observers are not removed correctly (unsubscribed), they can cause excessive memory usage (Lapsed Listener Problem). |
