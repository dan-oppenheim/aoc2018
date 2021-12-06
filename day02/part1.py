with open('input.txt') as input_file:
    two_count = 0
    three_count = 0
    for line in input_file:
        letters = {}
        for letter in line:
            if letter in letters:
                letters[letter] += 1
            else:
                letters[letter] = 1
        exactly_two = False
        exactly_three = False
        for _, v in letters.items():
            if v == 2:
                exactly_two = True
            elif v == 3:
                exactly_three = True

        if exactly_two: two_count += 1
        if exactly_three: three_count += 1

    print(f"Checksum: {two_count * three_count}")
