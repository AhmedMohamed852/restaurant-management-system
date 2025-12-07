import { Injectable } from '@angular/core';
import {jwtDecode} from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UserIdService {
  private readonly USER_ID_KEY = 'current_user_id';
  private readonly TOKEN_KEY = 'token'; // المكان اللي مخزن فيه التوكن (localStorage أو sessionStorage)

  constructor() { }

  // حفظ الـ userId في sessionStorage
  public saveUserId(userId: number): void {
    sessionStorage.setItem(this.USER_ID_KEY, userId.toString());
  }

  // جلب الـ userId من sessionStorage
  public getCurrentUserId(): number {
    const idString = sessionStorage.getItem(this.USER_ID_KEY);
    return idString ? parseInt(idString, 10) : 0;
  }

  // مسح الـ userId
  public clearUserId(): void {
    sessionStorage.removeItem(this.USER_ID_KEY);
  }

  // جلب الـ roles من التوكن
  public getRoles(): string[] {
    const token = localStorage.getItem(this.TOKEN_KEY) || sessionStorage.getItem(this.TOKEN_KEY);
    if (!token) return [];

    try {
      const decoded: any = jwtDecode(token);
      return decoded.roles || [];
    } catch (error) {
      console.error('Invalid token', error);
      return [];
    }
  }

  // جلب الـ userId مباشرة من التوكن (اختياري)
  public getUserIdFromToken(): number {
    const token = localStorage.getItem(this.TOKEN_KEY) || sessionStorage.getItem(this.TOKEN_KEY);
    if (!token) return 0;

    try {
      const decoded: any = jwtDecode(token);
      return decoded.sub ? parseInt(decoded.sub, 10) : 0;
    } catch (error) {
      console.error('Invalid token', error);
      return 0;
    }
  }
}
