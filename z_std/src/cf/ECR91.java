package cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ECR91 {

	public static void main(String army[]) throws Exception {

		a();

	}

	static void a() {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(scanner.nextLine());
			String[] line = scanner.nextLine().split(" ");

			int[] p = new int[n];
			for (int ii = 0; ii < line.length; ii++) {
				p[ii] = Integer.parseInt(line[ii]);
			}

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("NO");
			for (int j = 1; j < p.length - 1; j++) {
				if (p[j] > p[j - 1] && p[j] > p[j + 1]) {
					sBuilder.setLength(0);
					sBuilder.append("YES\n");
					sBuilder.append(j + " ");
					sBuilder.append(j + 1 + " ");
					sBuilder.append(j + 2 + " ");
					break;
				}
			}
			System.out.println(sBuilder.toString());

		}

	}

	static void a2nd() throws IOException {
		BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(infile.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();

		outer: while (T-- > 0) {
			int N = Integer.parseInt(infile.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(infile.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			for (int a = 0; a < N; a++) {
				int b = -1;
				int c = -1;
				for (int i = a + 1; i < N; i++) {
					if (arr[i] > arr[a] && b == -1)
						b = i;
					if (b != -1 && arr[b] > arr[i])
						c = i;
				}
				if (c != -1) {
					a++;
					b++;
					c++;
					sb.append("YES\n").append(a + " " + b + " " + c + "\n");
					continue outer;
				}
			}
			sb.append("NO\n");
		}
		System.out.print(sb);

	}

}
