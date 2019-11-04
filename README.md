
New Relic Code Challenge

Write a server (“Application”) in Java that opens a socket and restricts input to at most 5 concurrent clients. Clients will connect to the Application and write any number of 9 digit numbers, and then close the connection. The Application must write a de-duplicated list of these numbers to a log file in no particular order.

NOTE: Java 13.0.1 was used and there are no dependencies.

Requirements

1. The Application must accept input from at most 5 concurrent clients on TCP/IP port 4000.

2. Input lines presented to the Application via its socket must either be composed of exactly nine decimal digits (e.g.: 314159265 or 007007009) immediately followed by a server-native newline sequence; or a termination sequence as detailed in #9, below.

3. Numbers presented to the Application must include leading zeros as necessary to ensure they are each 9 decimal digits.

4. The log file, to be named "numbers.log”, must be created anew and/or cleared when the Application starts.

5. Only numbers may be written to the log file. Each number must be followed by a server-native newline sequence.

6. No duplicate numbers may be written to the log file.

7. Any data that does not conform to a valid line of input should be discarded and the client connection terminated immediately and without comment.

8. Every 10 seconds, the Application must print a report to standard output:

	A. The difference since the last report of the count of new unique numbers that        have been received.

	B. The difference since the last report of the count of new duplicate numbers that have been received.

	C. The total number of unique numbers received for this run of the Application.

	D. Example text for #8: Received 50 unique numbers, 2 duplicates. Unique total: 567231

9. If any connected client writes a single line with only the word "terminate" followed by a server-native newline sequence, the Application must disconnect all clients and perform a clean shutdown as quickly as possible.

10. Clearly state all of the assumptions you made in completing the Application.
# Code-Challenge
