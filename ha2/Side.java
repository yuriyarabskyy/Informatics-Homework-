public class Side
{
	@FunctionalInterface
	private interface Expression<T>
	{
		public int apply(T x, T y, T z);
	}

	private static class Reference
	{
		public Reference(int value)
		{
			this.v = value;
			this.original = value;
		}

		public void reset()
		{
			this.v = this.original;
		}

		public String toString()
		{
			return Integer.toString(this.v);
		}

		public int v;

		private int original;
	}

	public static class Calculator
	{
		public Calculator(int x, int y, int z)
		{
			this.x = new Reference(x);
			this.y = new Reference(y);
			this.z = new Reference(z);
		}

		public void calculate(Expression<Reference> expression)
		{
			int result = expression.apply(x, y, z);

			System.out.println("Result: " + result);

			System.out.println("x = " + x);
			System.out.println("y = " + y);
			System.out.println("z = " + z);

			System.out.println("-----------");

			reset();
		}

		private void reset()
		{
			x.reset();
			y.reset();
			z.reset();
		}

		private Reference x;
		private Reference y;
		private Reference z;
	}

	public static void main(String[] args)
	{
		int x = 1;
		int y = 42;
		int z = 99;

		Calculator calculator = new Calculator(x, y, z);

		// (a)
		calculator.calculate((a, b, c) -> {
			return (a.v--) * (++b.v)- c.v--;
		});

		// (b)
		calculator.calculate((a, b, c) -> {
			return (a.v = --c.v) + (c.v = 2 * b.v) - b.v-- + b.v++;
		});

		// (c)
		calculator.calculate((a, b, c) -> {
			return c.v = (a.v = (b.v + (a.v = ++b.v)- --c.v * a.v++) + 42);
		});
    System.out.println((x=--z)+(z=2*y)- y-- + y++);
    System.out.println(x+" "+y+" "+z);

	}
}
