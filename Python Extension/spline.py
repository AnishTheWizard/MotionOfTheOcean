from scipy import interpolate
import numpy as np
import matplotlib.pyplot as pp

x = np.array([i for i in range(1, 6)])
y = np.array([2, 3, 4, 5, 1])
pp.plot(x, y)

f = interpolate.interp1d(x, y, kind='cubic')
xnew = [i for i in range(0, 6, 0.01)]
ynew = f(xnew)

pp.plot(xnew, ynew)

pp.show()