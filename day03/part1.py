class Claim:
    def __init__(self, claim_str, cloth):
        id, at, coord, size = claim_str.split()
        self.id = int(id[1:])
        self.x, self.y = [int(p) for p in coord[:-1].split(',')]
        self.w,self.h = [int(p) for p in size.split('x')]
        self._add_to_cloth(cloth)

    def _add_to_cloth(self, cloth):
        for i in range(self.x, self.x + self.w):
            for j in range(self.y, self.y + self.h):
                key = (i,j)
                if key in cloth:
                    cloth[key].append(self)
                else:
                    cloth[key] = [self]

    def is_overlapped(self, cloth):
        for i in range(self.x, self.x + self.w):
            for j in range(self.y, self.y + self.h):
                key = (i,j)
                if len(cloth[key]) > 1:
                    return True
        return False


with open('input.txt') as input_file:
    cloth = {}
    claims = []
    for line in input_file:
        claims.append(Claim(line, cloth))
    print(f"There are {len(cloth)} used square inches of cloth")
    overlap_count = 0
    for _, v in cloth.items():
        if len(v) > 1:
            overlap_count += 1
    print(f"There are {overlap_count} overlapped square inches of cloth")

    for claim in claims:
        if not claim.is_overlapped(cloth):
            print(f"Only the claim with id {claim.id} is not overlapped")
            break

