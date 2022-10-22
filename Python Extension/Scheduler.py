import sched

def schedule(interval: int, callback, args):
    sched.scheduler.enterabs()