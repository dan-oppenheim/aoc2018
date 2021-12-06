def part1():
    with open('input.txt') as input_file:
        frequency = 0
        for line in input_file:
            frequency += int(line)
        print(f"End frequency is {frequency}")
    

def part2():
     with open('input.txt') as input_file:
        changes = [int(x) for x in input_file.readlines()]
        frequency = 0
        frequencies_seen = set([0])     
        
        def process_changes(changes, frequency, frequencies_seen):
            for change in changes:
                frequency += change
                if frequency in frequencies_seen:
                    return (frequency, True)
                else:
                    frequencies_seen.add(frequency)
            return (frequency, False)

        found_repeated_frequency = False
        while not found_repeated_frequency:
            frequency, found_repeated_frequency = process_changes(changes, frequency, frequencies_seen)

        print(f"First repeated frequency is {frequency}")


part2()