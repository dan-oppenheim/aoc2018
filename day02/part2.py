with open('input.txt') as input_file:
    ids = input_file.readlines()
    ids.sort()

    for x in range(len(ids)-1):
        id0 = ids[x]
        id1 = ids[x+1]

        diff_count = 0
        diff_index = 0
        for i in range(len(id0)):
            if id0[i] != id1[i]:
                diff_count += 1
                diff_index = i
                if diff_count > 1:
                    break
        if diff_count == 1:
            print(id0[:diff_index] + id0[diff_index+1:])
            break