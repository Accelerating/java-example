local size = redis.call("llen", KEYS[1])
local opsuccess = false
if (size < tonumber(ARGV[2]))
then
    redis.call("lpush", KEYS[1], ARGV[1])
    opsuccess = true
end
return opsuccess
