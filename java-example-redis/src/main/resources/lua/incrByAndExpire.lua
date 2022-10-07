local cur = redis.call("incrby", KEYS[1], ARGV[1])
redis.call("expire", KEYS[1], ARGV[2])
return cur