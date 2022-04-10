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
    
      // Loop for a session in minutes, then take break
      for minute <- 0 until duration do
        oneMinute()
        progressBar(f"Session $session", minute, duration)

      // We take a break here
      if session != sessions then
        for minute <- 0 until interlude do
          oneMinute()
          print(s"\rPause: ${interlude-minute} minutes left")
      
      if session == sessions then
        println("All sessions completed.")

      
  def tick(verbose: Boolean = false): Unit =
    if verbose then println("tick")
    Thread.sleep(1000)

  def oneMinute(): Unit =
    for _ <- 1 to SECONDS_PER_MINUTE do
      tick()

  def progressBar(task:String, fill:Int, shrink:Int): Unit =
    print(s"\r$task [${"-"*fill}>${"."*(shrink-fill-1)}]")
    if shrink - fill - 1 == 0 then print(" ✔️\n")


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


  def get(url: String): String = scala.io.Source.fromURL(url).mkString
