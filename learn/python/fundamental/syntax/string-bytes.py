my_str = 'unicode'
my_double_quotes = "unicode"
my_bytes = b'ascii' # like a byte array

print(my_str == my_double_quotes, " should be True")
print(my_bytes)
for b in my_bytes:
    print(b)


