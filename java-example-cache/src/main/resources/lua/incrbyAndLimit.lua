local cur_val = redis.call("get", KEYS[1])
local opsuccess = 0
local op_value = tonumber(ARGV[1])
if(not(cur_val) or (op_value + tonumber(cur_val)) <= tonumber(ARGV[2]))
then
    cur_val = redis.call("incrby", KEYS[1], ARGV[1])
    opsuccess = 1
end
return {opsuccess, cur_val}

