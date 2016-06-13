import zipfile,os.path, shutil

temp_dir = os.path.join(os.curdir, "temp")

# Clean out temp dir
if os.path.exists(temp_dir):
    for child in os.listdir(temp_dir):
        child_file = os.path.join(temp_dir, child)
        if os.path.isfile(child_file):
            os.remove(child_file)
        elif os.path.isdir(child_file):
            shutil.rmtree(child_file)
else:
    os.mkdir(temp_dir)

# Zip up the syntax folder
my_zip = os.path.join(temp_dir, "my_zip.zip")
my_zip_op = zipfile.ZipFile(my_zip, "w", zipfile.ZIP_DEFLATED)
# Adding files from directory 'files'
for root, dirs, files in os.walk(os.path.join(os.curdir, "syntax")):
    for f in files:
        my_zip_op.write(os.path.join(root, f))
my_zip_op.close()

# Unzip to temp dir
my_zip_ip = zipfile.ZipFile(my_zip)
my_zip_ip.extractall(temp_dir)
my_zip_ip.close()
