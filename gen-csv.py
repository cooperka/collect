# https://pandas.pydata.org/

import string
import pandas

# Flatten a list of lists https://stackoverflow.com/a/952952/763231
flatten = lambda x: [item for sublist in x for item in sublist]

NUM_LETTERS = 3

alphabet = list(string.ascii_lowercase[:NUM_LETTERS])

permutation1 = alphabet
frame1 = pandas.DataFrame({'list_name': 'first', 'name': permutation1, 'label': permutation1})
finalFrame = frame1

permutation2 = list(x + y for x in alphabet for y in alphabet)
frame2 = pandas.DataFrame({'list_name': 'second', 'name': permutation2, 'label': permutation2})
frame2 = frame2.assign(first = lambda x: x.name.str.split('').str[1])
finalFrame = finalFrame.append(frame2, sort=False)

permutation3 = list(x + y + z for x in alphabet for y in alphabet for z in alphabet)
frame3 = pandas.DataFrame({'list_name': 'third', 'name': permutation3, 'label': permutation3})
frame3 = frame3.assign(first = lambda x: x.name.str.split('').str[1])
frame3 = frame3.assign(second = lambda x: x.name.str.split('').str[1] + x.name.str.split('').str[2])
finalFrame = finalFrame.append(frame3, sort=False)

permutation4 = list(x + y + z + a for x in alphabet for y in alphabet for z in alphabet for a in alphabet)
frame4 = pandas.DataFrame({'list_name': 'fourth', 'name': permutation4, 'label': permutation4})
frame4 = frame4.assign(first = lambda x: x.name.str.split('').str[1])
frame4 = frame4.assign(second = lambda x: x.name.str.split('').str[1] + x.name.str.split('').str[2])
frame4 = frame4.assign(third = lambda x: x.name.str.split('').str[1] + x.name.str.split('').str[2] + x.name.str.split('').str[3])
finalFrame = finalFrame.append(frame4, sort=False)

finalFrame.to_csv('choices.csv', index=False)
