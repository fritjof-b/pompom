// Make a get request for motivational quotes
// def get(url: String): String = scala.io.Source.fromURL(url).mkString

val DURATION = 25
val SESSIONS = 4
val BREAK_DURATION = 5
// val SECONDS_PER_MINUTE = 60
val SECONDS_PER_MINUTE = 1

object Pompom:

  // Maybe add a help feature, 
  // not entirely sure how we should run program
  // SBT or just scala?
  val usage: String = """
      Pompom - a CLI pomodoro timer
      
      Usage:
        [--d duration in minutes]
        [--b break duration in minutes]
        [--n number of sessions]

        """

  def main(args:Array[String]): Unit = 
    val argList = args.toList
    val options = consumeArgList(Map(), argList)
    if options.isEmpty then
      println(usage)
    else
      session(
          duration = options.getOrElse("d", DURATION),
          sessions = options.getOrElse("s", SESSIONS),
          interlude = options.getOrElse("b", BREAK_DURATION))


  def session(duration:Int, sessions:Int, interlude:Int): Unit =   

    for session <- 1 to sessions do
      println(s"Session ${session} out of ${sessions} started.\n")
    
      for minute <- 0 until duration do
        oneMinute()
        progressBar(minute, duration)
    
      println()

      if session < sessions then
        println("Session completed - time for a break.")
      else
        println("All sessions completed.")

      for _ <- 1 to interlude do
        tick()

      
  def tick(verbose: Boolean = false): Unit =
    if verbose then println("tick")
    Thread.sleep(1000)

  def oneMinute(): Unit =
    for _ <- 1 to SECONDS_PER_MINUTE do
      tick()

  def progressBar(fill:Int, shrink:Int): Unit =
    print(s"\r[${"-"*fill}>${"."*(shrink-fill-1)}]")


  def consumeArgList(map:Map[String, Int], l:List[String]): Map[String, Int] =    
    l match
      case Nil => map
      case "--d" :: value :: tail =>
        consumeArgList(map ++ Map("d" -> value.toInt), tail)
      case "--b" :: value :: tail =>
        consumeArgList(map ++ Map("b" -> value.toInt), tail)
      case "--s" :: value :: tail =>
        consumeArgList(map ++ Map("s" -> value.toInt), tail)
      case _ =>
        Map()