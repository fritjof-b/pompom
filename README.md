# Pompom

Requirements: `Scala 3`, `Sbt`

This is a Pomodoro timer for the command line

## Todo

- [x] Add method for making GET-request
- [ ] Add motivational quotes between sessions 💪 
- [ ] Add logging support to track progress over days (maybe .csv?) 
- [ ] Proper testing
- [ ] Look into GUI libraries

## How to use

1. Clone this repo
2. Start by either running through SBT or plain Scala.

## Flags

You can pass parameters to the program (session timer, break timer and number of sessions) in the following way:

```
Usage:
[--d duration in minutes]
[--b break duration in minutes]
[--n number of sessions]
```

### Example

```sh
$ scala Pompom --d 45 --b 10 --n 4
```