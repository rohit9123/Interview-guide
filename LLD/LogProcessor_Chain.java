/*
 * PRACTICE QUESTION: Logger System using Chain of Responsibility
 *
 * Scenario:
 * Implement a logging system where different log levels are handled by different processors in a chain.
 *
 * Levels:
 * 1. INFO  (1): Just basic info.
 * 2. DEBUG (2): Debugging data.
 * 3. ERROR (3): Critical errors.
 *
 * Rule:
 * - A processor implementation should check if it can handle the log level.
 * - If it can, it logs it.
 * - Independently, it may call the next processor in the chain (or the design might specify that only one handles it).
 *
 * In this implementation, we will use a design where higher levels INCLUDE lower levels or simply pass down if the criteria matches.
 * Let's assume a strictly hierarchical logger:
 * - INFO logger handles INFO.
 * - DEBUG logger handles DEBUG.
 * - ERROR logger handles ERROR.
 * - If a request comes for ERROR, it might traverse the chain until it finds the ERROR handler.
 *
 * However, a common Chain pattern variation is: "Attempt to handle, then pass to next".
 */

