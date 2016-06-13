import zipfile, os.path, shutil, pathlib

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

# Zip up the syntax folder
my_zip = temp_dir.joinpath("my_zip.zip")
# Adding files from directory 'files'
with zipfile.ZipFile(str(my_zip), "w", zipfile.ZIP_DEFLATED) as my_zip_op:
    for root, dirs, files in os.walk(os.path.join(os.curdir, "syntax")):
        for f in files:
            my_zip_op.write(os.path.join(root, f))

# Unzip to temp dir
with zipfile.ZipFile(str(my_zip)) as my_zip_ip:
    my_zip_ip.extractall(str(temp_dir))
