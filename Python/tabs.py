import os

files = os.listdir()
count = 0
for file in files:
    if file[-3:] == '.py' and not file[:5] == 'test_':
        text = open(file).read()
        if '\t' in text:
            count += 1
            print(file)
print(count, 'submissions have tabs')
