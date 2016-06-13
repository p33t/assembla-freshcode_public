# pathlib seems to be a later generation, stricter method of working with paths.
import pathlib

temp_dir = pathlib.Path.cwd().joinpath("temp")

def clear_dir(pth):
    for child in pth.iterdir():
        if child.is_file():
            child.unlink()
        elif child.is_dir():
            clear_dir(child)
            child.rmdir()

# Clean out temp dir
if temp_dir.exists():
    clear_dir(temp_dir)
else:
    temp_dir.mkdir()

print(temp_dir, ' cleared')

# Converting to string!!!!
print(str(temp_dir))