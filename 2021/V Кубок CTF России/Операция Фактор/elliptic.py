
class EllipticPoint:
    def __init__(self, _X, _Y) -> None:
        self.X = _X
        self.Y = _Y
        self.isInfinity = False
    def __str__(self) -> str:
        return "( {} , {} )".format(self.X, self.Y)
    def __eq__(self, other) -> bool:
        if isinstance(other, self.__class__):
            if other.X == self.X and other.Y == self.Y:
                return True
        return False
    def setInfinity(self):
        self.isInfinity = True
    def pack(self) -> bytes:
        return self.X.to_bytes(16, byteorder='big') + self.Y.to_bytes(16, byteorder='big')

class EllipticCurve:
    def __init__(self, _a, _b, _p) -> None:
        self.a = _a
        self.b = _b
        self.p = _p
    def __str__(self) -> str:
        return "E_{} ({},{})".format(self.p, self.a, self.b)
    def pack(self) -> bytes:
        return self.a.to_bytes(16, byteorder='big') + self.b.to_bytes(16, byteorder='big')+ self.p.to_bytes(16, byteorder='big')


class EllipticUtil:
    @staticmethod
    def isCurveSingular(curve: EllipticCurve) -> bool:
        d = ( 4*(curve.a**3) + 27*(curve.b ** 2) ) % curve.p
        return True if d != 0 else False

    @staticmethod
    def isPointOnCurve(curve: EllipticCurve, point: EllipticPoint) -> bool:
        if point.isInfinity:
            return True
        res_left = (point.Y ** 2) % curve.p
        res_right = (point.X ** 3 + curve.a * point.X + curve.b) % curve.p
        return True if res_left == res_right else False

    @staticmethod
    def producePoint(curve:EllipticCurve, point1: EllipticPoint, point2: EllipticPoint) -> EllipticPoint:
        if not EllipticUtil.isPointOnCurve(curve, point1) or not EllipticUtil.isPointOnCurve(curve, point2):
            raise ValueError("One of the point not on curve.")
        if point1.isInfinity:
            return point2
        if point2.isInfinity:
            return point1
        if point1 == point2:
            #R = P+P = 2P
            k = ((3*(point1.X**2)+curve.a)*pow(2*point1.Y, -1, curve.p)) % curve.p
            new_x = (k**2 - 2*point1.X) % curve.p
            new_y = (k*(point1.X-new_x) - point1.Y) % curve.p
            res = EllipticPoint(new_x, new_y)
            #self check
            if EllipticUtil.isPointOnCurve(curve, res):
                return res
            else:
                raise ValueError("Bad producePoint() result.")
        else:
            #R = P+Q
            k = ((point2.Y - point1.Y)*pow(point2.X - point1.X,-1,curve.p)) % curve.p
            new_x = (k**2 - point1.X - point2.X) % curve.p
            new_y = (k*(point1.X-new_x) - point1.Y) % curve.p
            res = EllipticPoint(new_x, new_y)
            #self check
            if EllipticUtil.isPointOnCurve(curve, res):
                return res
            else:
                raise ValueError("Bad producePoint() result.")

    @staticmethod
    def multiplicatePoint(curve: EllipticCurve, point: EllipticPoint, n: int) -> EllipticPoint:
        Q = EllipticPoint(0,0); Q.setInfinity();
        if point.isInfinity or n == 0:
            return Q
        else:
            for i in bin(n)[2:]:
                Q = EllipticUtil.producePoint(curve, Q, Q)
                if int(i):
                    Q = EllipticUtil.producePoint(curve, Q, point)
            #self check
            if EllipticUtil.isPointOnCurve(curve, Q):
                return Q
            else:
                raise ValueError("Bad multiplicatePoint() result.")

        



